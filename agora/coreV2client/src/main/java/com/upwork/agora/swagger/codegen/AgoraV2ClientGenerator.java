package com.upwork.agora.swagger.codegen;

import io.swagger.codegen.*;
import io.swagger.codegen.languages.AbstractJavaCodegen;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Swagger;

import java.util.*;
import java.util.stream.Collectors;

public class AgoraV2ClientGenerator extends AbstractJavaCodegen {
    private HashMap<String, String> modelFqns = new HashMap<>();

    public CodegenType getTag() {
        return CodegenType.CLIENT;
    }

    public String getName() {
        return "agoraV2Client";
    }

    public String getHelp() {
        return "Generates a agoraV2Client CAL module.";
    }

    public AgoraV2ClientGenerator() {
        super();

        outputFolder = "generated-code/agoraV2Client";

        apiTemplateFiles.clear();
        apiTemplateFiles.put("hysCmd.mustache", ".java");

        templateDir = "agoraV2Client";
    }

    @Override
    public void addOperationToGroup(String tag, String resourcePath, Operation operation, CodegenOperation co, Map<String, List<CodegenOperation>> operations) {
        operations.put(co.operationId, Collections.singletonList(co));
    }

    @Override
    public String toApiName(String name) {
        return initialCaps(name) + "HysCmd";
    }


    @Override
    public String toModelImport(String name) {
        String modelFqn = modelFqns.get(name);
        return modelFqn == null ? super.toModelImport(name) : modelFqn;
    }

    @Override
    public void preprocessSwagger(Swagger swagger) {
        super.preprocessSwagger(swagger);
        apiPackage = "com.upwork.agora." + swagger.getInfo().getTitle() + ".command";
        invokerPackage = apiPackage;
        modelPackage = "com.upwork.agora." + swagger.getInfo().getTitle() + ".model";

        swagger.getDefinitions().forEach((name, model) -> {
            Object modelFqn = model.getVendorExtensions().get("x-fqn");
            if (modelFqn != null) {
                modelFqns.put(name, modelFqn.toString());
            }
        });

        String commandFactoryName = initialCaps(swagger.getInfo().getTitle()) + "HysCmdFactory";
        additionalProperties.put("commandFactoryName", commandFactoryName);
        supportingFiles.add(new SupportingFile("commandFactory.mustache", sourceFolder + "/" + apiPackage().replace('.', '/'), commandFactoryName + ".java"));
    }

    @Override
    public Map<String, Object> postProcessSupportingFileData(Map<String, Object> objs) {
        super.postProcessSupportingFileData(objs);
        objs.put("imports", modelFqns.values().stream().map(fqn -> Collections.singletonMap("import", fqn)).collect(Collectors.toList()));
        return objs;
    }

    @Override
    public CodegenOperation fromOperation(String path, String httpMethod, Operation operation, Map<String, Model> definitions, Swagger swagger) {
        CodegenOperation op = super.fromOperation(path, httpMethod, operation, definitions, swagger);
        op.httpMethod = httpMethod.toLowerCase();
        return op;
    }
}