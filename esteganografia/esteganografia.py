from PIL import Image
import sys

def decode(im, w, h):
    res = []
    buffer = []
    for i in range(w):
        for j in range(h):
            for p in range(3):
                buffer.append(im[i, j][p] & 1)
                if len(buffer) == 8:
                    res.append(chr(int("".join(str(x) for x in buffer), 2)))
                    if res[-1] == '\x00':
                        return "".join(res[:-1])
                    buffer = []
    return ""

def encode(im, w, h, msg):
    i = j = p = 0
    bits = []
    for c in msg:
        aux = ord(c)
        for bit in reversed(range(8)):
            bits.append((aux & 1 << bit) >> bit)
    bits = bits + [0, 0, 0, 0, 0, 0, 0, 0]
    for b in bits:
        if p == 0:
            if b == 0:
                im[i, j] = (im[i, j][0] & 254, im[i, j][1], im[i, j][2])
            else:
                im[i, j] = (im[i, j][0] | 1, im[i, j][1], im[i, j][2])
        if p == 1:
            if b == 0:
                im[i, j] = (im[i, j][0], im[i, j][1] & 254, im[i, j][2])
            else:
                im[i, j] = (im[i, j][0], im[i, j][1] | 1, im[i, j][2])
        if p == 2:
            if b == 0:
                im[i, j] = (im[i, j][0], im[i, j][1], im[i, j][2] & 254)
            else:
                im[i, j] = (im[i, j][0], im[i, j][1], im[i, j][2] | 1)
        p = p + 1
        if p == 3:
            p = 0
            j += 1
        if j == h:
            j = 0
            i += 1
            if i == w:
                print("No cabe el mensaje")
                break


def main():
    if len(sys.argv) == 1:
        print("\n\tEncode: -e {fichero_origen} {Mensaje} {fichero_destino}")
        print("\tDecode: -d {fichero_origen}")
        exit(0)
    if sys.argv[1] == "-e":
        image = Image.open(sys.argv[2])
        w,h = image.size
        i = image.load()
        encode(i, w, h, sys.argv[3])
        image.save(sys.argv[4])
        print("Guardado en", sys.argv[4])
        exit(0)
    if sys.argv[1] == "-d":
        image = Image.open(sys.argv[2])
        w,h = image.size
        i = image.load()
        print(decode(i, w, h))
        exit(0)
    else:
        print("Opcion no reconocida.")
        exit(1)

if __name__ == '__main__':
    main()
