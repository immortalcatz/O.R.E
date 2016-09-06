package com.acronym.ore.proxy;

import com.acronym.ore.common.reference.Reference;

import javax.script.ScriptEngineManager;

/**
 * Created by Jared on 8/21/2016.
 */
public class CommonProxy {

    public void registerKeybindings() {}

    public void initEngines(){
        ScriptEngineManager manager = new ScriptEngineManager();
        Reference.ENGINE_JAVASCRIPT = manager.getEngineByExtension("js");
        System.out.println(">>> " + Reference.ENGINE_JAVASCRIPT);
    }

}
