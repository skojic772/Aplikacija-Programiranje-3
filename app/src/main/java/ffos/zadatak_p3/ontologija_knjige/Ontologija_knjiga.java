package ffos.zadatak_p3.ontologija_knjige;

import java.io.Serializable;

public class Ontologija_knjiga implements Serializable {


    private int sifra;
    private String nazivKnjige;
    private String autor;
    private String zanr;
    private String biljeska;

    public Ontologija_knjiga(int sifra, String nazivKnjige, String autor, String zanr, String biljeska) {
        this.sifra = sifra;
        this.nazivKnjige = nazivKnjige;
        this.autor = autor;
        this.zanr = zanr;
        this.biljeska = biljeska;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getNazivKnjige() {
        return nazivKnjige;
    }

    public void setNazivKnjige(String nazivKnjige) {
        this.nazivKnjige = nazivKnjige;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public String getBiljeska() {
        return biljeska;
    }

    public void setBiljeska(String biljeska) {
        this.biljeska = biljeska;
    }
}



