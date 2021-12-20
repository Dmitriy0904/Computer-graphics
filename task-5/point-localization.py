import matplotlib.pyplot as plt

MAX_RAY_LENGTH = 1000

def isInsidePolygon(points, p):
    n = len(points)   

    if n < 3:
        return False
    
    extreme = (MAX_RAY_LENGTH, p[1])
    count = i = 0
     
    while True:
        next = (i + 1) % n
         
        if (doIntersect(points[i],points[next], p, extreme)):
            if orientation(points[i], p, points[next]) == 0 :
                return onSegment(points[i], p, points[next])                 
            count += 1
        i = next
        if (i == 0):
            break

    if count % 2 == 1:
        return True
    else:
        return False

def orientation(p, q, r):
    val = (((q[1] - p[1]) *(r[0] - q[0])) -((q[0] - p[0]) *(r[1] - q[1])))
    if val == 0: return 0
    if val > 0: return 1
    else: return -1

def onSegment(p, q, r): 
    if ((q[0] <= max(p[0], r[0])) &
        (q[0] >= min(p[0], r[0])) &
        (q[1] <= max(p[1], r[1])) &
        (q[1] >= min(p[1], r[1]))):
        return True
    return False

def doIntersect(p1, q1, p2, q2):
    o1 = orientation(p1, q1, p2)
    o2 = orientation(p1, q1, q2)
    o3 = orientation(p2, q2, p1)
    o4 = orientation(p2, q2, q1)
    
    if (o1 != o2) and (o3 != o4):
        return True
     
    if (o1 == 0) and (onSegment(p1, p2, q1)):
        return True
 
    if (o2 == 1) and (onSegment(p1, q2, q1)):
        return True
    
    if (o3 == 1) and (onSegment(p2, p1, q2)):
        return True
 
    if (o4 == 1) and (onSegment(p2, q1, q2)):
        return True
    
    return False


polygon = [ (1, 1), (2, 2), (4, 2), (5, 1) ]
polygon.append(polygon[0])

point = (3, 1.5)

res = isInsidePolygon(polygon, point)
if res: print ('Point is inside polygon')
else: print ('Point is not inside polygon')

xs, ys = zip(*polygon)
plt.plot(xs,ys)
plt.plot(point[0],point[1], 'ro')
plt.show()



