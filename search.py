import operator
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




def preprocess(extensionsToCheck,maxScore,minScore):
	f = open('full.txt', 'r')
	peoples =[]
	scount=0
	ListGenre = ['action','romance','fantasy']
	ListPeople=['Kana Hanazawa']
	Result[]
	for x in range(0, 10):
		dataAnimu = f.readline() 
		S = dataAnimu.split("]")
		Genre  = S[1].replace("[","").replace("'","").split(", ") #genre yang ada
		#print Genre
		for g in ListGenre :
			if any(g in gen for gen in Genre):
				#print "found !", Genre
				if dataAnimu in Result: #data udah ada di list
				else :
					Result.append(dataAnimu)
				
				break
		for y in range(0,6) :
			peoples.extend (S[y].replace("[","").replace("'","").split(", "))
		#print peoples
		
		
		
		#print S
		#if any(ext in S for ext in extensionsToCheck):
		#	print "Found ",ext," in : ",S
		scount+=1
		#score = S[2].replace("'","").replace("[","").replace("\n","").replace("]","")
		#newd = S[input_jenis].replace('[','').split(", ")#no 2 itu score
	return peoples
#print VA

f = open('full.txt', 'r')
Arrf = []

while True :
	action = raw_input('Command :')
	preprocess(action)
