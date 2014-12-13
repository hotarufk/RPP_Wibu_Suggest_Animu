package RDFHelper;

import helper.Constants;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class RDFWriter {
	final private static String NS = Constants.NS;

	public static void main(String args[]) {
		final OntModel ontModel = subclassOntModel();
		try {
			FileOutputStream out = new FileOutputStream(Constants.ONTOLOGY_FILE);
			ontModel.write(out, "RDF/XML-ABBREV");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static OntModel subclassOntModel() {
		final OntModel model = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		final OntClass anime = model.createClass(NS + "Anime");
		final OntClass tv = model.createClass(NS + "Tv");
		final OntClass ova = model.createClass(NS + "Ova");
		final OntClass movie = model.createClass(NS + "Movie");
		
		final OntClass genre = model.createClass(NS + "Genre");
		final OntClass action = model.createClass(NS + "action");
		final OntClass adventure = model.createClass(NS + "adventure");
		
		final OntProperty hasGenre = model.createOntProperty(NS + "hasGenre");
		
		anime.addSuperClass(model.createSomeValuesFromRestriction(null, hasGenre, genre));

		// Add subclass
		anime.addSubClass(tv);
		anime.addSubClass(ova);
		anime.addSubClass(movie);
		
		genre.addSubClass(action);
		

		// Add Individual
		tv.createIndividual(NS + "Gatchaman_(TV)").addProperty(hasGenre, action).addProperty(hasGenre, adventure);
		ova.createIndividual(NS + "Guyver_(TV)").addProperty(hasGenre, adventure);
		return model;
	}

}
