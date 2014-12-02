import operator
import re
#cara Pakai :
#S itu data RAW nya , ada 0-9 data , 
#data [1] itu genre
#data [2] itu score
#data [3] itu Director
#data [4] itu Storyboard
#data [5] itu character design
#data [6] itu sound director
#data [7] itu producer
#data [8] itu animation producer
#data [9] itu VA


def preprocess_zero(animeTitle=None):
	if animeTitle == None:
		return []
	else:
		return preprocess(10.0, 0.0, ['action', 'science fiction'], []);

def preprocess(maxScore=None,minScore=None,LGenre=None,LKeywords=None):
	#Kamus Kecil
	if minScore == '':
		minScore = -1
	if maxScore == '':
		maxScore = -1
	#minScore = float(minScore)
	#maxScore = float(maxScore)

	#if maxScore >= 0 and maxScore <= 10 :
	#	mxS = maxScore
	#else:
	mxS = 10
	#if minScore >= 0 and minScore <= 10:
	#	mmS = minScore
	#else:
	mmS = 0
	
	ListGenre = ['action'] # dummy
	if LGenre != None:
		ListGenre = LGenre
	ListKeywords=['Kana Hanazawa'] # dummy
	if LKeywords != None :
		ListKeywords = LKeywords
	Result=[]
	
	#Algoritma
	f = open('full.txt', 'r')
	for x in range(0, 998):
		count = 0
		keywords = []
		dataAnimu = f.readline() 
		S = dataAnimu.split("]")
		
		#title
		title = S[0].replace("'","").split(", [")[1].replace("[","")
		
		#pisahain jenis nya dari title
		hasil = re.search(r"\(.*\)$",title)
		ans = hasil.group(0)
		#print (x+1," regex : ",ans.replace("(","").replace(")",""))
		rdf="<Thing rdf:about='http://www.semanticweb.org/lenovo/ontologies/2014/11/untitled-ontology-3#"+title.replace(ans,"").replace(" ","")+"'>"
		rdf+="\n        <rdf:type rdf:resource='&owl;NamedIndividual'/>"
		
		#Type
		rdf+="\n        <rdf:type>"
		rdf+="\n            <Restriction>"
		rdf+="\n                <onProperty rdf:resource='http://www.semanticweb.org/lenovo/ontologies/2014/11/untitled-ontology-3#hasType'/>"
		rdf+="\n                <someValuesFrom rdf:resource='http://www.semanticweb.org/lenovo/ontologies/2014/11/untitled-ontology-3#"+ans.replace("(","").replace(")","")+"Type'/>"
		rdf+="\n            </Restriction>"
		rdf+="\n        </rdf:type>"

		#url
		URL = S[0].replace("[","").replace("'","").split(", ")[0]
		#print URL
		
		#Genre
		genre  = S[1].replace("[","").replace("'","").split(", ") #genre yang ada
		for ListGenreEntity in ListGenre:
			if any(ListGenreEntity in gen for gen in genre):
				#print "found !", Genre
				count += 2
		del genre[0]
		#loop for genre
		for x in genre :
			rdf+="\n        <rdf:type>"
			rdf+="\n            <Restriction>"
			rdf+="\n                <onProperty rdf:resource='http://www.semanticweb.org/lenovo/ontologies/2014/11/untitled-ontology-3#hasTheme'/>"
			rdf+="\n                <someValuesFrom rdf:resource='http://www.semanticweb.org/lenovo/ontologies/2014/11/untitled-ontology-3#"+x.replace(" ","")+"Theme'/>"
			rdf+="\n            </Restriction>"
			rdf+="\n        </rdf:type>"
		rdf+="\n        </Thing>"
		print (rdf)
				
		#score
		score  = float(S[2].replace("[","").replace("'","").replace(", ","")) #genre yang ada
		if (mmS < score) and (score < mxS):
			#print "testses"
			count += 3
			
		#KeyWords
		for y in range(0,9) :
			keywords.extend (S[y].replace("[","").replace("'","").split(", "))
		for ListKeywordsEntity in ListKeywords:
			if any(ListKeywordsEntity in pp for pp in keywords):
				#print "found !", Genre
				count += 2
				
		#currentAnimu = Animu (Title = title,Genre = genre , Score = score, Director = "dummy", VA = "Dummy",Link = URL,Count = count)
		#print "curANIMU",currentAnimu
		#Result.append(currentAnimu)
		
	#Result.sort(key=operator.attrgetter('Count'))
	#Suggestion = list(reversed(Result))[0:10]
	#for x in Suggestion :
	#	print (x.Title,x.Score)
	#	print ("count :",x.Count)
	return ""

preprocess()