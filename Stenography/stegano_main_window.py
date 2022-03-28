# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'steganmain.ui'
#
# Created by: PyQt5 UI code generator 5.14.2
#
# WARNING! All changes made in this file will be lost!


from PyQt5 import QtCore, QtGui, QtWidgets


class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(710, 609)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.verticalLayout_2 = QtWidgets.QVBoxLayout(self.centralwidget)
        self.verticalLayout_2.setObjectName("verticalLayout_2")
        self.horizontalLayout = QtWidgets.QHBoxLayout()
        self.horizontalLayout.setObjectName("horizontalLayout")
        self.file_name_label = QtWidgets.QLabel(self.centralwidget)
        font = QtGui.QFont()
        font.setPointSize(12)
        font.setBold(False)
        font.setWeight(50)
        self.file_name_label.setFont(font)
        self.file_name_label.setObjectName("file_name_label")
        self.horizontalLayout.addWidget(self.file_name_label)
        self.input_file_line = QtWidgets.QLineEdit(self.centralwidget)
        self.input_file_line.setObjectName("input_file_line")
        self.horizontalLayout.addWidget(self.input_file_line)
        self.btn_browse = QtWidgets.QPushButton(self.centralwidget)
        self.btn_browse.setObjectName("btn_browse")
        self.horizontalLayout.addWidget(self.btn_browse)
        self.verticalLayout_2.addLayout(self.horizontalLayout)
        self.horizontalLayout_2 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_2.setObjectName("horizontalLayout_2")
        self.label = QtWidgets.QLabel(self.centralwidget)
        font = QtGui.QFont()
        font.setPointSize(12)
        self.label.setFont(font)
        self.label.setObjectName("label")
        self.horizontalLayout_2.addWidget(self.label)
        self.output_file_line = QtWidgets.QLineEdit(self.centralwidget)
        self.output_file_line.setObjectName("output_file_line")
        self.horizontalLayout_2.addWidget(self.output_file_line)
        self.verticalLayout_2.addLayout(self.horizontalLayout_2)
        self.verticalLayout = QtWidgets.QVBoxLayout()
        self.verticalLayout.setObjectName("verticalLayout")
        self.label_2 = QtWidgets.QLabel(self.centralwidget)
        font = QtGui.QFont()
        font.setPointSize(12)
        self.label_2.setFont(font)
        self.label_2.setObjectName("label_2")
        self.verticalLayout.addWidget(self.label_2)
        self.text_field = QtWidgets.QTextEdit(self.centralwidget)
        self.text_field.setObjectName("Blad")
        self.verticalLayout.addWidget(self.text_field)
        self.verticalLayout_2.addLayout(self.verticalLayout)
        self.horizontalLayout_3 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_3.setObjectName("horizontalLayout_3")
        self.btn_encode = QtWidgets.QPushButton(self.centralwidget)
        self.btn_encode.setObjectName("btn_encode")
        self.horizontalLayout_3.addWidget(self.btn_encode)
        self.btn_decode = QtWidgets.QPushButton(self.centralwidget)
        self.btn_decode.setObjectName("btn_decode")
        self.horizontalLayout_3.addWidget(self.btn_decode)
        self.verticalLayout_2.addLayout(self.horizontalLayout_3)
        self.label_3 = QtWidgets.QLabel(self.centralwidget)
        font = QtGui.QFont()
        font.setPointSize(12)
        self.label_3.setFont(font)
        self.label_3.setObjectName("label_3")
        self.verticalLayout_2.addWidget(self.label_3)
        self.outputWindow = QtWidgets.QTextBrowser(self.centralwidget)
        self.outputWindow.setObjectName("outputWindow")
        self.verticalLayout_2.addWidget(self.outputWindow)
        MainWindow.setCentralWidget(self.centralwidget)
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)
        self.btn_browse.clicked.connect(self.browseSlot)
        self.input_file_line.returnPressed.connect(self.returnedPressedSlot)
        self.btn_encode.clicked.connect(self.encodeSlot)
        self.btn_decode.clicked.connect(self.decodeSlot)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def returnedPressedSlot(self):
        pass

    def browseSlot(self):
        pass

    def encodeSlot(self):
        pass

    def decodeSlot(self):
        pass

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "SekretnySzyfr"))
        self.file_name_label.setText(_translate("MainWindow", "Plik Wejscia"))
        self.btn_browse.setText(_translate("MainWindow", "Wybierz"))
        self.label.setText(_translate("MainWindow", "Plik Zapisz:"))
        self.label_2.setText(_translate("MainWindow", "Sekretny tekst:"))
        self.btn_encode.setText(_translate("MainWindow", "Zakoduj"))
        self.btn_decode.setText(_translate("MainWindow", "Rozkoduj"))
        self.label_3.setText(_translate("MainWindow", "Debug:"))


if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    MainWindow = QtWidgets.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    sys.exit(app.exec_())
