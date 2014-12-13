package core;

import RDFHelper.RDFReader;

import com.hp.hpl.jena.ontology.OntModel;

public class Main {
	
	public static void main(String args[]) {
		final OntModel ontModel = RDFReader.readRDF();
	}
}
