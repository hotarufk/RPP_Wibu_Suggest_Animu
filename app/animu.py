class Animu:
#data [1] itu genre
#data [2] itu score
#data [3] itu Director
#data [4] itu Storyboard
#data [5] itu character design
#data [6] itu sound director
#data [7] itu producer
#data [8] itu animation producer
#data [9] itu VA
  def __init__(self,Title,Genre, Score, Director,VA,Link,Storyboard=None,Character_Design=None,Sound_Director=None,Producer=None,Animation_Producer=None,Count = None):
    self.Title = Title
    self.Score = Score
    self.Genre = Genre
    self.VA = VA
    self.Director = Director
    self.Link = Link
    if Count != None :
		self.Count = Count
