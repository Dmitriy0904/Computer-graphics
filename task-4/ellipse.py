from PIL import Image

scale = 30  # Масштаб
size = 5000  # Размер картинки


def setPixel(image, xs, ys):  # Функция установки пикселей учитывая масштаб
    for x in range(scale):
        for y in range(scale):
            image.putpixel((scale * xs + x, scale * ys + y), (255, 0, 0))

    image.putpixel((x, y), (255, 0, 0))


def put4pixels(image, x, xm, y, ym):  # Установление 4-х пикселей относительно осей симметрии
    setPixel(image, xm - x, ym + y)
    setPixel(image, xm + x, ym + y)
    setPixel(image, xm + x, ym - y)
    setPixel(image, xm - x, ym - y)


def drawEllipse(image, xm, ym, a, b):  # Отрисовка эллипса
    x = -a
    y = 0
    e2 = b * b
    err = x * (2 * e2 + x) + e2
    while (x <= 0):
        put4pixels(image, x, xm, y, ym)
        e2 = 2 * err
        if (e2 >= (x * 2 + 1) * b * b):
            x += 1
            err += (x * 2 + 1) * b * b
        if (e2 <= (y * 2 + 1) * a * a):
            y += 1
            err += (y * 2 + 1) * a * a

    while (y < b):  # Обработка ситуации если радиус элипса равен 1
        setPixel(image, xm, ym + y)
        setPixel(image, xm, ym - y)
        y += 1



def main():
    im = Image.new('RGB', (size, size))
    drawEllipse(im, 70, 70, 35, 50)
    im.show()


if __name__ == "__main__":
    main()
