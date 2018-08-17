package com.gmail.user0abc.sandbox;

import java.io.File;

/**
 * Created by eseiivv on 10/25/2017.
 */
public class Boo {
    public static void main(String[] arg){
        //objectModel();
        //transferObjects();
    }

    static void objectModel(){
        File xml = new File("D:/idea_ws/eai/Granite_OSS_Core_dev/OSSCore/projects/oss-core-nd-mt/source/definitions/oss-core-nd-mt.object-model.xml");
        //ObjectModelBundle bundle = JAXB.unmarshal(xml, ObjectModelBundle.class);
        //System.out.println("ObjectModelBundle = "+bundle);
    }

    static void transferObjects(){
        File xml = new File("D:/idea_ws/eai/Granite_OSS_Core_dev/OSSCore/projects/oss-core-nd-mt/generated/resources/oss-core-nd-mt.advanced-commands.transfer-objects.xml");
        //COMPOSEDOBJECTS bundle = JAXB.unmarshal(xml, COMPOSEDOBJECTS.class);
        //System.out.println("ComposedObject = "+bundle);
    }


}
