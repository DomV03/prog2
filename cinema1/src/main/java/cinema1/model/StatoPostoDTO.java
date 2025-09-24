package cinema1.model;


public class StatoPostoDTO {
    private Long id;
    private Long postoId;
    private String codicePosto;
    private boolean occupato;

    public StatoPostoDTO(StatoPosto statoPosto) {
        this.id = statoPosto.getId();
        this.postoId = statoPosto.getPosto().getId();
        this.codicePosto = statoPosto.getPosto().getCodice();
        this.occupato = statoPosto.getOccupato();
    }

    public Long getId() {
        return id;
    }

    public Long getPostoId() {
        return postoId;
    }

    public String getCodicePosto() {
        return codicePosto;
    }

    public boolean isOccupato() {
        return occupato;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostoId(Long postoId) {
        this.postoId = postoId;
    }

    public void setCodicePosto(String codicePosto) {
        this.codicePosto = codicePosto;
    }

    public void setOccupato(boolean occupato) {
        this.occupato = occupato;
    }
}
