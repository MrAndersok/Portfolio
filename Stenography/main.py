from PyQt5 import QtCore, QtGui, QtWidgets
from PIL import Image
from PyQt5.QtCore import QObject, pyqtSlot
from stegano_main_window import Ui_MainWindow
import sys
from steganograpy import encode_text, decode_text


class MainWindowUIClass(Ui_MainWindow):
    def __init__(self):
        super().__init__()

    def setupUi(self, MW):
        super().setupUi(MW)

    def debugPrint(self, msg):
        self.outputWindow.append(msg)

    def returnedPressedSlot(self):
        fileName = self.input_file_line.text()
        if self.test_file(fileName):
            self.debugPrint('Open the file: ' + fileName)
            self.input_file_line.setText(fileName)
        else:
            m = QtWidgets.QMessageBox()
            m.setText('Invalid file name!\n' + fileName)
            m.setIcon(QtWidgets.QMessageBox.Warning)
            m.setStandardButtons(QtWidgets.QMessageBox.Ok | QtWidgets.QMessageBox.Cancel)
            m.setDefaultButton(QtWidgets.QMessageBox.Cancel)
            m.exec_()
            self.input_file_line.setText('')
            self.debugPrint('Invalid file specified: ' + fileName)

    def browseSlot(self):
        options = QtWidgets.QFileDialog.Options()
        options |= QtWidgets.QFileDialog.DontUseNativeDialog
        fileName, _ = QtWidgets.QFileDialog.getOpenFileName(None, 'Choose the image file', '', options=options)
        if fileName and self.test_file(fileName):
            self.debugPrint('Open the file: ' + fileName)
            self.input_file_line.setText(fileName)
        else:
            text = 'Invalid file name!\n' + fileName
            self.display_error(text)

    def encodeSlot(self):
        image_file = self.input_file_line.text()
        new_file = self.output_file_line.text()
        data = self.text_field.toPlainText()
        if not image_file or not new_file or not data:
            self.display_error('The image or new image name field or data to encode is empty')
            return
        try:
            encode_text(image_file, data, new_file)
        except Exception as e:
            self.display_error(e.message)
        else:
            m = QtWidgets.QMessageBox()
            m.setText('Data was succesfully encoded')
            m.setIcon(QtWidgets.QMessageBox.Information)
            m.setStandardButtons(QtWidgets.QMessageBox.Ok | QtWidgets.QMessageBox.Cancel)
            m.setDefaultButton(QtWidgets.QMessageBox.Cancel)
            m.exec_()
            self.debugPrint('Data was succesfully encoded')

    def decodeSlot(self):
        image_file = self.input_file_line.text()
        if not image_file:
            self.display_error('The image file to decode is empty')
            return
        self.outputWindow.append('Decoding...')
        try:
            data = decode_text(image_file)
        except Exception as e:
            self.display_error(e.message)
        else:
            self.text_field.clear()
            self.text_field.append(data)
            self.debugPrint('Data was succesfully decoded, the data is: ' + data)

    def test_file(self, file_name):
        try:
            im = Image.open(file_name)
            im.close()
            if not file_name.lower().endswith(('.png', '.jpg', '.jpeg', '.tiff', '.bmp', '.gif')):
                raise
        except Exception:
            return False
        else:
            return True

    def display_error(self, text):
        m = QtWidgets.QMessageBox()
        m.setText(text)
        m.setIcon(QtWidgets.QMessageBox.Warning)
        m.setStandardButtons(QtWidgets.QMessageBox.Ok | QtWidgets.QMessageBox.Cancel)
        m.setDefaultButton(QtWidgets.QMessageBox.Cancel)
        m.exec_()
        self.debugPrint(text)


def main():
    app = QtWidgets.QApplication(sys.argv)
    MainWindow = QtWidgets.QMainWindow()
    ui = MainWindowUIClass()
    ui.setupUi(MainWindow)
    MainWindow.show()
    sys.exit(app.exec_())


if __name__ == '__main__':
    main()
