from subprocess import Popen, PIPE, STDOUT
import json

# Note: We need to convert space (' ') to underscore ('_')

def inference(Title, param=None):
    parameters = ['java', '-jar', './lib/animeRecommendation.jar', Title]
    if param != None:
        parameters = parameters + param
    p = Popen(parameters, stdout=PIPE, stderr=STDOUT)
    output = ''
    for line in p.stdout:
        output = output + line
    j = json.loads(output)
    return j

print 'Title = Guyver_(TV) with no filter:'
print inference(Title='Guyver_(TV)')
print 'Title = Guyver_(TV) with "+B" filter:'
print inference(Title='Guyver_(TV)', param=['+B'])
print 'Title = Guyver_(TV) with "-A" and "+B" filters:'
print inference(Title='Guyver_(TV)', param=['-A', '+B'])