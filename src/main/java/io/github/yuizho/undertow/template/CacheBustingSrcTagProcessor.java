/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.yuizho.undertow.template;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.processor.StandardSrcTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * reference: https://github.com/thymeleaf/thymeleaf/blob/thymeleaf-3.0.8.RELEASE/src/main/java/org/thymeleaf/standard/processor/StandardSrcTagProcessor.java
 * @author yuizho
 */
public class CacheBustingSrcTagProcessor extends AbstractCustomAttributeModifierTagProcessor {

    public static final int PRECEDENCE = StandardSrcTagProcessor.PRECEDENCE;
    public static final String ATTR_NAME = StandardSrcTagProcessor.ATTR_NAME;

    public CacheBustingSrcTagProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, ATTR_NAME, PRECEDENCE, false);
    }

    @Override
    String modifyAttributeValue(String attributeValue) {
        // TODO: some meta data (built datetime, built version) is better.
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        return attributeValue + "?" + sdf.format(new Date());
    }
}
