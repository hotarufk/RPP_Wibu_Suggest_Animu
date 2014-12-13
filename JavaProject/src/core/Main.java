package core;

import helper.Constants;
import RDFHelper.RDFReader;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
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
	}
}
