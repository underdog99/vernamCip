/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.conf;

import org.springframework.stereotype.Component;

/**
 *
 * @author Mladen
 */

@Component
public class FileHandler {
    
    private int maxAction;
    private int currAction;

    public int getMaxAction() {
        return maxAction;
    }

    public void setMaxAction(int maxAction) {
        this.maxAction = maxAction;
    }

    public int getCurrAction() {
        return currAction;
    }

    public void setCurrAction(int currAction) {
        this.currAction = currAction;
    }
    
    
    public boolean canPerformAction(){
        return currAction < maxAction;
    }
    
}
