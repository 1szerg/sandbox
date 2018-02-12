package com.gmail.user0abc.sandbox;

import com.telcordia.granite.platform.composition.ComposedObject;
import com.telcordia.granite.sdk.generated.objectModel.ObjectModelBundle;
import tgp.tools.composition.generated.COMPOSEDOBJECTS;

import javax.xml.bind.JAXB;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

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
        ObjectModelBundle bundle = JAXB.unmarshal(xml, ObjectModelBundle.class);
        System.out.println("ObjectModelBundle = "+bundle);
    }

    static void transferObjects(){
        File xml = new File("D:/idea_ws/eai/Granite_OSS_Core_dev/OSSCore/projects/oss-core-nd-mt/generated/resources/oss-core-nd-mt.advanced-commands.transfer-objects.xml");
        COMPOSEDOBJECTS bundle = JAXB.unmarshal(xml, COMPOSEDOBJECTS.class);
        System.out.println("ComposedObject = "+bundle);
    }


}
