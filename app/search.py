import operator
from animu import Animu
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


def preprocess_zero(data, animeTitle=None):
	if animeTitle == None:
		return []
	else:
		for data_entity in data:
			if data_entity[1][0] == animeTitle:
				keyword_list = []
				for y in range(1,9):
					keyword_list.extend(data_entity[y])
				return preprocess(data, data_entity[1][0], data_entity[2], keyword_list)
	return []

def preprocess(data, currentTitle,LGenre=None,LKeywords=None):
	#Kamus Kecil
	ListGenre = ['action'] # dummy
	if LGenre != None:
		ListGenre = LGenre
	ListKeywords=['Kana Hanazawa'] # dummy
	if LKeywords != None :
		ListKeywords = LKeywords
	Result=[]

	for x in range(0, 1000):

		count = 0
		URL = data[x][0]
		title = data[x][1][0]
		genre = data[x][2]
		score = float(data[x][3][0])

		for ListGenreEntity in ListGenre:
			if any(ListGenreEntity in gen for gen in genre):
				count += 2	

		keywords = []
		for y in range(1,9):
			keywords.extend(data[y])

		for ListKeywordsEntity in ListKeywords:
			if any(ListKeywordsEntity in pp for pp in keywords):
				count += 2
				
		currentAnimu = Animu (Title = title,Genre = genre , Score = score, Director = "dummy", VA = "Dummy",Link = URL,Count = count)

		if (title != currentTitle):
			Result.append(currentAnimu)	
		
	Result.sort(key=operator.attrgetter('Count'))
	Suggestion = list(reversed(Result))[0:10]

	# for x in Suggestion :
	# 	print x.Title,x.Score
	# 	print "count :",x.Count
	return Suggestion
