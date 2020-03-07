#!/usr/bin/env perl
#$pre = "find . -type f -name '*.tex' | xargs sed -i '' -e 's/、/，/g' -e 's/。/．/g'";
$pre = "find . -type f -name '*.tex'";
$latex = "${pre}; platex -synctex=1 -halt-on-error %O %S";
$latex_silent = "${pre}; platex -halt-on-error -interaction=batchmode %O %S";
$bibtex = 'pbibtex %O %B';
$dvipdf = 'dvipdfmx %O -o %D %S';
$makeindex = 'mendex -U %O -o %D %S';
$pdf_mode = 3;
$max_repeat = 5;
$pvc_view_file_via_temporary = 0;
