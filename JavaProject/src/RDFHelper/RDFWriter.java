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
		
		/** Anime Class */
		final OntClass anime = model.createClass(NS + "Anime");
		final OntClass tv = model.createClass(NS + "Tv");
		final OntClass ova = model.createClass(NS + "Ova");
		final OntClass movie = model.createClass(NS + "Movie");
		
		/** Genre Class */
		final OntClass genre = model.createClass(NS + "Genre");
		final OntClass action = model.createClass(NS + "action");
		final OntClass adventure = model.createClass(NS + "adventure");
		final OntClass tournament = model.createClass(NS + "tournament");
		final OntClass thriller  = model.createClass(NS + "thriller");
		final OntClass horror  = model.createClass(NS + "horror");
		final OntClass psychological = model.createClass(NS + "psychological");
		final OntClass mystery  = model.createClass(NS + "mystery");
		final OntClass romance  = model.createClass(NS + "romance");
		final OntClass drama  = model.createClass(NS + "drama");
		final OntClass slice_of_life  = model.createClass(NS + "slice_of_life");
		final OntClass comedy = model.createClass(NS + "comedy");
		final OntClass erotica  = model.createClass(NS + "erotica");
		final OntClass magic  = model.createClass(NS + "magic");
		final OntClass science_fiction  = model.createClass(NS + "science_fiction");
		final OntClass supernatural  = model.createClass(NS + "supernatural");
		final OntClass fantasy  = model.createClass(NS + "fantasy");
		
		
		/** Category Class */
		final OntClass category = model.createClass(NS + "category");
		final OntClass categoryA = model.createClass(NS + "categoryA");
		final OntClass categoryB = model.createClass(NS + "categoryB");
		final OntClass categoryC = model.createClass(NS + "categoryC");
		final OntClass categoryD = model.createClass(NS + "categoryD");
		final OntClass categoryE = model.createClass(NS + "categoryE");
		final OntClass categoryF = model.createClass(NS + "categoryF");
		
		/** Name Class */
		final OntClass name  = model.createClass(NS + "name");
		
		/** Properties */
		final OntProperty hasGenre = model.createOntProperty(NS + "hasGenre");
		final OntProperty hasName = model.createOntProperty(NS + "hasName");
		final OntProperty hasMember = model.createOntProperty(NS + "hasMember");
		
		anime.addSuperClass(model.createSomeValuesFromRestriction(null, hasGenre, genre));
		anime.addSuperClass(model.createHasValueRestriction(null, hasName, name));
		category.addSuperClass(model.createSomeValuesFromRestriction(null, hasMember, genre));

		// Add subclass
		anime.addSubClass(tv);
		anime.addSubClass(ova);
		anime.addSubClass(movie);
		
		category.addSubClass(categoryA);
		category.addSubClass(categoryB);
		category.addSubClass(categoryC);
		category.addSubClass(categoryD);
		category.addSubClass(categoryE);
		category.addSubClass(categoryF);
		
		genre.addSubClass(action);
		genre.addSubClass(adventure);
		genre.addSubClass(tournament);
		genre.addSubClass(thriller);
		genre.addSubClass(horror);
		genre.addSubClass(psychological);
		genre.addSubClass(mystery);
		genre.addSubClass(romance);
		genre.addSubClass(drama);
		genre.addSubClass(slice_of_life);
		genre.addSubClass(comedy);
		genre.addSubClass(erotica);
		genre.addSubClass(magic);
		genre.addSubClass(science_fiction);
		genre.addSubClass(supernatural);
		genre.addSubClass(fantasy);
		
		// Add Category relation
		categoryA.addProperty(hasMember, action);
		categoryA.addProperty(hasMember, adventure);
		categoryA.addProperty(hasMember, tournament);
		categoryB.addProperty(hasMember, thriller);
		categoryB.addProperty(hasMember, horror);
		categoryB.addProperty(hasMember, psychological);
		categoryB.addProperty(hasMember, mystery);
		categoryC.addProperty(hasMember, romance);
		categoryC.addProperty(hasMember, drama);
		categoryC.addProperty(hasMember, slice_of_life);
		categoryD.addProperty(hasMember, comedy);
		categoryE.addProperty(hasMember, erotica);
		categoryF.addProperty(hasMember, magic);
		categoryF.addProperty(hasMember, science_fiction);
		categoryF.addProperty(hasMember, supernatural);
		categoryF.addProperty(hasMember, fantasy);

		// Add Individuals here
		tv.createIndividual(NS + "Gatchaman_(TV)").addProperty(hasName, "Gatchaman_(TV)").addProperty(hasGenre, horror);
		tv.createIndividual(NS + "Guyver_(TV)").addProperty(hasName, "Guyver_(TV)").addProperty(hasGenre, adventure);
		tv.createIndividual(NS + "Boogiepop_Phantom_(TV)").addProperty(hasName, "Boogiepop_Phantom_(TV)").addProperty(hasGenre, adventure);
		return model;
	}

}
