package RDFHelper;

import helper.Constants;

import java.io.InputStream;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class RDFReader {

	public static OntModel readRDF() {
		// create an empty model
		OntModel model = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_DL_MEM);

		// use the FileManager to find the input file
		InputStream in = FileManager.get().open(Constants.ONTOLOGY_FILE);
		if (in == null) {
			throw new IllegalArgumentException("File not found");
		}

		// read the RDF/XML file
		model.read(in, null);

		// write it to standard out
		// model.write(System.out);

		return model;
	}

}
