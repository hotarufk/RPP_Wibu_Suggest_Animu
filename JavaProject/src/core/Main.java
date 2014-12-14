package core;

import helper.Constants;
import RDFHelper.RDFReader;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class Main {

	public static void main(String args[]) {
		// Turn off logger
		org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
		
		String anime_title = "";
		
		if (args.length != 1) {
			if (Constants.VERSION == Constants.DEBUG) {
				anime_title = "Guyver_(TV)";	
			} else {
				return;
			}
		} else anime_title = args[0];
		
		final OntModel ontModel = RDFReader.readRDF();

		/** SPARQL Query for inferencing anime genre to its category and retrieving all animes from all inferenced categories */
		// Mapping a title to its genre -> get all category -> retrieve all genres in all categories
		String queryString = "SELECT DISTINCT ?anime_result_name WHERE {"
				+ "?anime_entity <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasName>  \""+ anime_title +"\" ."
				+ "?anime_entity <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasGenre> ?genre ."
				+ "?category <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasMember> ?genre ."
				+ "?category <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasMember> ?related_genre ."
				+ "?anime_result_entity <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasGenre> ?related_genre .";
		
		// Find whether TV / OVA / Movie in Title
		queryString = queryString + "?anime_result_entity a <http://www.semanticweb.org/lenovo/ontologies/2014/12/Tv> .";
		
		// Retrieve Anime name
		queryString = queryString + "?anime_result_entity <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasName> ?anime_result_name ";
		
		// Subtract original title from result set
		queryString = queryString + "MINUS { ?anime_result_entity <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasName>  \""+ anime_title +"\" }}";
		
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, ontModel);
		ResultSet results = qe.execSelect();

		// Output query results
		ResultSetFormatter.outputAsJSON(results);

		// Important - free up resources used running the query
		qe.close();
	}
}
