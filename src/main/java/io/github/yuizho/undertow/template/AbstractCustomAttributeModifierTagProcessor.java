package io.github.yuizho.undertow.template;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeDefinition;
import org.thymeleaf.engine.AttributeDefinitions;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.engine.IAttributeDefinitionsAware;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.processor.AbstractStandardExpressionAttributeTagProcessor;
import org.thymeleaf.standard.util.StandardProcessorUtils;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.util.EscapedAttributeUtils;
import org.thymeleaf.util.Validate;

/**
 * reference: https://github.com/thymeleaf/thymeleaf/blob/thymeleaf-3.0.8.RELEASE/src/main/java/org/thymeleaf/standard/processor/AbstractStandardAttributeModifierTagProcessor.java
 * @author yuizho
 */
public abstract class AbstractCustomAttributeModifierTagProcessor
        extends AbstractStandardExpressionAttributeTagProcessor
        implements IAttributeDefinitionsAware {

    private final boolean removeIfEmpty;
    private final String targetAttrCompleteName;

    private AttributeDefinition targetAttributeDefinition;

    protected AbstractCustomAttributeModifierTagProcessor(
            final TemplateMode templateMode,
            final String dialectPrefix, final String attrName,
            final int precedence, final boolean removeIfEmpty) {
        this(templateMode, dialectPrefix, attrName, attrName, precedence, removeIfEmpty);
    }

    protected AbstractCustomAttributeModifierTagProcessor(
            final TemplateMode templateMode, final String dialectPrefix,
            final String attrName, final String targetAttrCompleteName,
            final int precedence, final boolean removeIfEmpty) {

        super(templateMode, dialectPrefix, attrName, precedence, false);

        Validate.notNull(targetAttrCompleteName, "Complete name of target attribute cannot be null");

        this.targetAttrCompleteName = targetAttrCompleteName;
        this.removeIfEmpty = removeIfEmpty;

    }

    public void setAttributeDefinitions(final AttributeDefinitions attributeDefinitions) {
        Validate.notNull(attributeDefinitions, "Attribute Definitions cannot be null");
        // We precompute the AttributeDefinition of the target attribute in order to being able to use much
        // faster methods for setting/replacing attributes on the ElementAttributes implementation
        this.targetAttributeDefinition = attributeDefinitions.forName(getTemplateMode(), this.targetAttrCompleteName);
    }

    @Override
    protected void doProcess(
            final ITemplateContext context,
            final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final Object expressionResult,
            final IElementTagStructureHandler structureHandler) {

        final String newAttributeValue
                = EscapedAttributeUtils.escapeAttribute(getTemplateMode(), expressionResult == null ? null : expressionResult.toString());
        final String modifiedAttributeValue = modifyAttributeValue(newAttributeValue);

        // These attributes might be "removable if empty", in which case we would simply remove the target attribute...
        if (this.removeIfEmpty && (modifiedAttributeValue == null || modifiedAttributeValue.length() == 0)) {

            // We are removing the equivalent attribute name, without the prefix...
            structureHandler.removeAttribute(this.targetAttributeDefinition.getAttributeName());
            structureHandler.removeAttribute(attributeName);

        } else {

            // We are setting the equivalent attribute name, without the prefix...
            StandardProcessorUtils.replaceAttribute(
                    structureHandler, attributeName, this.targetAttributeDefinition, this.targetAttrCompleteName, (modifiedAttributeValue == null ? "" : modifiedAttributeValue));

        }
    }

    abstract String modifyAttributeValue(final String attributeValue);
}
