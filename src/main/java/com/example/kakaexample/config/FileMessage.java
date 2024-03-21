package com.example.kakaexample.config;



public class FileMessage {
    private byte[] fileBytes;
    private String type;

    private String raisonSociale;

    public String getRaisonSociale(){
        return raisonSociale;
    }

    public void setRaisonSociale(String raison_sociale){
        this.raisonSociale = raison_sociale;
    }

    // Getters and setters (optional, but good practice)
    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
