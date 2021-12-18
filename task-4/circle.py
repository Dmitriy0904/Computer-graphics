from PIL import Image

scale = 30  # Масштаб
size = 5000  # Размер


def setPixel(image, xs, ys):  # Функция установки пикселей учитывая масштаб
    for x in range(scale):
        for y in range(scale):
            if (scale * xs + x >= 0 and scale * ys + y >= 0):
                image.putpixel((scale * xs + x, scale * ys + y), (255, 0, 0))

    image.putpixel((x, y), (255, 0, 0))


def put4pixels(img, x, xm, y, ym):  # Установка сразу 4-х пикселей относительно осей симметрии
    setPixel(img, xm - x, ym + y)  # 1-я координатная четверть
    setPixel(img, xm - y, ym - x)  # 2-я координатная четверть
    setPixel(img, xm + x, ym - y)  # 3-я координатная четверть
    setPixel(img, xm + y, ym + x)  # 4-я координатная четверть


def drawCircle(img, xm, ym, r):  # Отрисовка круга
    x = -r
    y = 0
    err = 2 - 2 * r

    while (x < 0):

        put4pixels(img, x, xm, y, ym)
        r = err
        if (r <= y):
            y += 1
            err += y * 2 + 1
        if (r > x or err > y):
            x += 1
            err += x * 2 + 1


def main():
    im = Image.new('RGB', (size, size))
    drawCircle(im, 80, 80, 30)
    im.show()


if __name__ == "__main__":
    main()
