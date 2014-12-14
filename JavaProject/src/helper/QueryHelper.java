package helper;

public class QueryHelper {

	public static String composeQuery(String anime_title, String[] args) {
		String queryString = "SELECT * WHERE {";

		if (args.length > 1) {
			queryString = queryString + "{";
		}

		// Mapping a title to its genre -> get all category -> retrieve all genres in all categories
		queryString = queryString
				+ "SELECT DISTINCT ?anime_result_name WHERE {"
				+ "?anime_entity <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasName>  \""
				+ anime_title
				+ "\" . "
				+ "?anime_entity <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasGenre> ?genre . "
				+ "?category <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasMember> ?genre . "
				+ "?category <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasMember> ?related_genre . "
				+ "?anime_result_entity <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasGenre> ?related_genre . ";

		// Find whether TV / OVA / Movie in Title
		queryString = queryString + checkSubClass(anime_title);
		// Retrieve Anime name
		queryString = queryString
				+ "?anime_result_entity <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasName> ?anime_result_name ";

		// Subtract original title from result set
		queryString = queryString
				+ "MINUS { ?anime_result_entity <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasName>  \""
				+ anime_title + "\" }}";

		// Add additional category filtering (ex: "+A" , "-B", etc)
		if (args.length > 1) {
			queryString = queryString + "}";
			int count = 0;
			for (int j = 2; j <= args.length; j++) {
				if (args[j - 1].charAt(0) == '+') {
					queryString = queryString
							+ constructFilterQuery(anime_title, args[j - 1], 0);
				} else if (args[j - 1].charAt(0) == '-')
					count++;
			}
			if (count != 0) {
				for (int j = 2; j <= args.length; j++) {
					if (args[j - 1].charAt(0) == '-') {
						queryString = queryString
								+ constructFilterQuery(anime_title,
										args[j - 1], 1);
					}
				}
			}
		}
		queryString = queryString + "}";
		return queryString;
	}

	private static String checkSubClass(String anime_title) {
		String res = "";
		if (anime_title.toLowerCase().contains("(tv")) {
			res = "?anime_result_entity a <http://www.semanticweb.org/lenovo/ontologies/2014/12/Tv> . ";
		} else if (anime_title.toLowerCase().contains("(movie")) {
			res = "?anime_result_entity a <http://www.semanticweb.org/lenovo/ontologies/2014/12/Movie> . ";
		} else if (anime_title.toLowerCase().contains("(ova")) {
			res = "?anime_result_entity a <http://www.semanticweb.org/lenovo/ontologies/2014/12/Ova> . ";
		}
		return res;
	}

	private static String constructFilterQuery(String anime_title,
			String filter, int type) {
		String res = "";

		String ctg = "<http://www.semanticweb.org/lenovo/ontologies/2014/12/category"
				+ filter.charAt(1) + ">";
		if (type == 0)
			res = res + " UNION { ";
		else if (type == 1)
			res = res + " MINUS { ";
		res = res
				+ "SELECT DISTINCT ?anime_result_name WHERE { "
				+ ctg
				+ " <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasMember> ?related_genre . "
				+ "?anime_result_entity <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasGenre> ?related_genre . ";
		// Find whether TV / OVA / Movie in Title
		res = res + checkSubClass(anime_title);
		// Retrieve Anime name
		res = res
				+ "?anime_result_entity <http://www.semanticweb.org/lenovo/ontologies/2014/12/hasName> ?anime_result_name ";
		res = res + "} }";

		return res;
	}

}
