package com.example.savingapp;

import java.util.Arrays;

public class User {
    String email;


    String houses;
    Double buget,expnens,earning,intrate,loc,botloc,botexpn,botbug,botearn,botrate,debt,botdebt;

    public User() {
    }

    public User(String email, Double buget, Double expnens, Double earning, Double intrate,Double botexpn,Double botbug,Double botearn,Double botrate,Double debt,Double botdebt,Double loc,Double botloc,String houses) {
        this.email = email;
        this.botdebt=botdebt;
        this.earning=earning;
        this.intrate=intrate;
        this.debt= debt;
        this.buget=buget;
        this.expnens=expnens;
        this.botbug=botbug;
        this.botloc=botloc;
        this.botearn=botearn;
        this.botexpn=botexpn;
        this.loc=loc;
        this.botrate=botrate;
        this.houses=houses;

    }

    public Double getBuget() {
        return buget;
    }
    public Double getDebt()
    {
        return debt;
    }
    public Double getBotdebt()
    {
        return botdebt;
    }
    public void setBotdebt(Double botdebt)
    {
        this.botdebt= botdebt;
    }
    public String getHouses() {
        return houses;
    }
    public void setHouses(String houses)
    {
        this.houses=houses;
    }
    public Double getBotloc() {
        return botloc;
    }
    public Double getBotexpn() {
        return botexpn;
    }
    public Double getBotbug() {
        return botbug;
    }
    public Double getLoc() {
        return loc;
    }
    public Double getBotrate() {
        return botrate;
    }
    public Double getBotearn() {
        return botearn;
    }
    public Double getEarning()
    {
        return earning;
    }
    public Double getIntrate()
    {
        return intrate;
    }
    public Double getExpnens() {
        return expnens;
    }

    public void setBuget(Double buget) {
        this.buget = buget;
    }
    public void setBotloc(Double botloc) {
        this.botloc = botloc;
    }
    public void setDebt(Double debt)
    {
        this.debt= debt;
    }
    public void setBotexpn(Double botexpn) {
        this.botexpn = botexpn;
    }
    public void setBotbug(Double botbug) {
        this.botbug = botbug;
    }
    public void setBotearn(Double botearn) {
        this.botearn = botearn;
    }
    public void setBotrate(Double botrate) {
        this.botrate = botrate;
    }
    public void setLoc(Double loc)
    {
        this.loc=loc;
    }

    public void setExpnens(Double expnens) {
        this.expnens = expnens;
    }
    public void setEarning(Double earning){
        this.earning=earning;
    }
    public void setIntrate(Double intrate){
        this.intrate=intrate;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email= email;
    }
}
