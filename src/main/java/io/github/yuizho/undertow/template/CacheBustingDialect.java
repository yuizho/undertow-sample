/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.yuizho.undertow.template;

import java.util.LinkedHashSet;
import java.util.Set;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

/**
 *
 * @author yuizho
 */
public class CacheBustingDialect extends AbstractProcessorDialect {

    public CacheBustingDialect() {
        super("CacheBusting", "cash-busting", StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        // reference: https://github.com/thymeleaf/thymeleaf/blob/thymeleaf-3.0.8.RELEASE/src/main/java/org/thymeleaf/standard/StandardDialect.java#L421
        final Set<IProcessor> processors = new LinkedHashSet<IProcessor>();
        processors.add(new CacheBustingSrcTagProcessor(dialectPrefix));
        return processors;
    }
}
