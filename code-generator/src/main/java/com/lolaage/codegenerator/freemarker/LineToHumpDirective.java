package com.lolaage.codegenerator.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * @description:
 * @author: lijiayu
 * @date: 2020-03-06 17:41
 **/
@Component
public class LineToHumpDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        Writer out = env.getOut();
        Object value = params.get("value");
        String valueStr = String.valueOf(value);
        String result = Tool.lineToHump(valueStr);

        out.write(result);
    }

}
