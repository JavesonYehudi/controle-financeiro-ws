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

	public MongoDBProducer() {
		Morphia morphia = new Morphia();
		morphia.mapPackage("br.com.controlefinanceiro");
		//String uri = "mongodb://javeson:FXMTmOU64YEsTiLA@cluster0-shard-00-00-xk8og.mongodb.net:27017,cluster0-shard-00-01-xk8og.mongodb.net:27017,cluster0-shard-00-02-xk8og.mongodb.net:27017/<DATABASE>?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin";
		datastore = morphia.createDatastore(new MongoClient(new MongoClientURI(System.getenv("MONGO_DB_CONNECTOR"))), "test"/*System.getenv("MONGO_DB")*/);
	}

	public void dispose(@Disposes Datastore datastore) { datastore.getMongo().close(); }

	@RequestScoped
	@Produces
	public Datastore provide() { return datastore; }

}