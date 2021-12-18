from PIL import Image

scale = 30  # Масштаб
size = 5000  # Размер


def setPixel(image, xs, ys):  # Функция установки пикселей учитывая масштаб
    for x in range(scale):
        for y in range(scale):
            if (scale * xs + x >= 0 and scale * ys + y >= 0):
                image.putpixel((scale * xs + x, scale * ys + y), (255, 0, 0))

    image.putpixel((x, y), (255, 0, 0))


def bresenham(image, x1=0, y1=0, x2=0, y2=0):
    dx = x2 - x1
    dy = y2 - y1

    sign_x = 1 if dx > 0 else -1 if dx < 0 else 0
    sign_y = 1 if dy > 0 else -1 if dy < 0 else 0

    if dx < 0: dx = -dx
    if dy < 0: dy = -dy

    if dx > dy:
        pdx, pdy = sign_x, 0
        es, el = dy, dx
    else:
        pdx, pdy = 0, sign_y
        es, el = dx, dy

    x, y = x1, y1

    error, t = el / 2, 0

    setPixel(image, x, y)

    while t < el:
        error -= es
        if error < 0:
            error += el
            x += sign_x
            y += sign_y
        else:
            x += pdx
            y += pdy
        t += 1
        setPixel(image, x, y)


def main():
    im = Image.new('RGB', (size, size))
    # bresenham(im, 0, 0, 40, 50)
    bresenham(im, 20, 30, 100, 120)
    im.show()

if __name__ == "__main__":
    main()