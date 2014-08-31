package services;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.EntradaCalculo;
import model.MapaMalha;
import model.RespostaWebService;

import org.codehaus.jettison.json.JSONObject;

import bll.CalculoRotasBll;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.CalculoRotaException;
import exception.PersistenciaGrafoExcpetion;



/**
 * Classe que será exposta para os webServices
 * @author Chris
 *
 */
@Path("rotas")
public class RotasService {

	
	/**
	 * Método web para persistência de mapa na base
	 * @param json
	 * @return json
	 */
	@PUT
	@Path("/gravaMapa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespostaWebService gravaMapa(JSONObject json) {
		RespostaWebService resp = new RespostaWebService();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			MapaMalha malha = objectMapper.readValue(json.toString(),
					MapaMalha.class);
			CalculoRotasBll calc = new CalculoRotasBll();
			calc.gravaGrafo(malha);
			resp.setResultado("mapaSalvo");

		} catch (PersistenciaGrafoExcpetion e) {
			resp.setResultado(e.getMessage());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	/**
	 * Método web para calculo da rota
	 * @param json
	 * @return json
	 */
	@POST
	@Path("/calculo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespostaWebService calcularRota(JSONObject json) {
		RespostaWebService resp = new RespostaWebService();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			EntradaCalculo entrada = objectMapper.readValue(json.toString(),EntradaCalculo.class);
			CalculoRotasBll calc = new CalculoRotasBll();
			resp=calc.calculaRota(entrada);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CalculoRotaException e) {
			resp.setResultado(e.getMessage());
		}
		return resp;
	}
}
