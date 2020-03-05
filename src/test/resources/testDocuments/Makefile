all:
	find . -type f -name '*.tex' | xargs latexmk -pdfdvi

clean:
	find . -type f -name '*.tex' | xargs latexmk -c
	find . -type f -name '*.dvi' | xargs rm
	find . -type f -name '*.gz' | xargs rm

view:
	find . -type f -name '*.tex' | xargs latexmk -pv
	latexmk -pv $(MAIN)

watch:
	find . -type f -name '*.tex' | xargs latexmk -pvc
