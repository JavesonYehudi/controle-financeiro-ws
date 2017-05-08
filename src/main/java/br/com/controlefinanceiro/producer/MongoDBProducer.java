package br.com.controlefinanceiro.producer;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@RequestScoped
public class MongoDBProducer implements Serializable{

	private static final long serialVersionUID = 1L;
	private Datastore datastore;
	//private final String uri = "";
	
	public MongoDBProducer() {
		Morphia morphia = new Morphia();
		morphia.mapPackage("br.com.controlefinanceiro");
		MongoClientURI mongoClientURI = new MongoClientURI(System.getenv("MONGO_DB"));
		datastore = morphia.createDatastore(new MongoClient(mongoClientURI), "test");
	}

	public void dispose(@Disposes Datastore datastore) {
		datastore.getMongo().close();
	}

	@RequestScoped
	@Produces
	public Datastore provide() {
		return datastore;
	}

}