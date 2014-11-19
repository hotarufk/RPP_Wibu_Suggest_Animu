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




def preprocess(maxScore=None,minScore=None,LGenre=None,LKeywords=None):
	#Kamus Kecil
	keywords = []
	if maxScore !=None :
		mxS = maxScore
	else:
		mxS = 10
	if minScore !=None :
		mmS = minScore
	else:
		mmS = 0
	ListGenre = ['action','romance','fantasy']
	if LGenre != None:
		ListGenre = LGenre
	ListKeywords=['Kana Hanazawa']
	if LKeywords != None :
		ListKeywords = LKeywords
	Result=[]
	
	#Algoritma
	f = open('full.txt', 'r')
	for x in range(0, 10):
		count = 0
		dataAnimu = f.readline() 
		S = dataAnimu.split("]")
		#title
		title = S[0].replace("[","").replace("'","").split(", ")[1]
		print title
		#url
		URL = S[0].replace("[","").replace("'","").split(", ")[0]
		print URL
		#Genre
		genre  = S[1].replace("[","").replace("'","").split(", ") #genre yang ada
		for g in ListGenre :
			if any(g in gen for gen in genre):
				#print "found !", Genre
				count += 5
				if dataAnimu in Result: #data udah ada di list
					print "sudah ada"
				else :
					#Result.append(dataAnimu)
					print "found1"
				break
		
		#score
		score  = float(S[2].replace("[","").replace("'","").replace(", ","")) #genre yang ada
		print score
		if (mmS < score) and (score < mxS):
			#print "testses"
			count += 3
			if dataAnimu in Result: #data udah ada di list
				print "sudah ada"
			else :
				#Result.append(dataAnimu)
				print "found2"
		#KeyWords
		for y in range(0,9) :
			keywords.extend (S[y].replace("[","").replace("'","").split(", "))
		for p in ListKeywords:
			if any(p in pp for pp in keywords):
				#print "found !", Genre
				count += 2
				if dataAnimu in Result: #data udah ada di list
					print "sudah ada"
				else :
					#Result.append(dataAnimu)
					print "found3"
				break
		currentAnimu = Animu (Title = title,Genre = genre , Score = score, Director = "dummy", VA = "Dummy",Link = URL,Count = count)
		print "curANIMU",currentAnimu
		Result.append(currentAnimu)
	Result.sort(key=operator.attrgetter('Count'))
	for x in Result :
		print "count :",x.Count
	return list(reversed(Result))
#print VA

f = open('full.txt', 'r')
#Arrf = []

#while True :
	#action = raw_input('Command :')
preprocess()
