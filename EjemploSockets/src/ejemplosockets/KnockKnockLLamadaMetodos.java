/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplosockets;

/**
 *
 * @author Gabriel
 */
public class KnockKnockLLamadaMetodos implements ILlamadaMetodos {

    private final KnockKnockProtocol p = new KnockKnockProtocol();

    @Override
    public String LlamarMetodo(String Metodo) {
       return p.processInput(Metodo);
    }

}
