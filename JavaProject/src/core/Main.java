package core;

import helper.Constants;
import RDFHelper.RDFReader;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.NodeIterator;

public class Main {
	final private static String NS = Constants.NS;

	public static void main(String args[]) {
		final OntModel ontModel = RDFReader.readRDF();
		final OntClass tv = ontModel.createClass(NS + "Tv");

		final OntProperty hasGenre = ontModel
				.createOntProperty(NS + "hasGenre");

		// ResIterator iter = ontModel.listResourcesWithProperty(hasGenre,
		// action);
		NodeIterator iter = ontModel.listObjectsOfProperty(
				ontModel.getResource(NS + "Guyver_(TV)"), hasGenre);
		if (iter.hasNext()) {
			while (iter.hasNext()) {
				// Get all genres
				System.out.println(iter.nextNode().toString());

			}
		}

		/** SPARQL Query */
		String queryString = "SELECT ?x WHERE {" +
			  "?p <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasName>  \"Guyver_(TV)\"."+
				  "?p <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasGenre> ?y." +
				  "?x <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasGenre> ?y." +
				  "?x a <http://www.semanticweb.org/lenovo/ontologies/2014/12/Tv>"
				  + "MINUS {"
				  + "?x <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasName>  \"Guyver_(TV)\" }}";
		Query query = QueryFactory.create(queryString);
		
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, ontModel);
		ResultSet results = qe.execSelect();
		
		// Output query results
		ResultSetFormatter.out(System.out, results, query);
		
		// Important - free up resources used running the query
		qe.close();
	}
}
