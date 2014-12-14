from subprocess import Popen, PIPE, STDOUT
import json

# Note: We need to convert space (' ') to underscore ('_')

def inference(Title):
    p = Popen(['java', '-jar', './lib/animeRecommendation.jar', Title], stdout=PIPE, stderr=STDOUT)
    output = ''
    for line in p.stdout:
        output = output + line
    j = json.loads(output)
    return j

print inference('Guyver_(TV)')