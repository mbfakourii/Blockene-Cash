#!/usr/bin/gnuplot -persist

set term wxt 1 size 800,600 title 'transactions'
plot "report_transaction.txt" u 1:2

set term wxt 2 size 800,600 title 'Request'
plot "report_CanRequest.txt" u 1:2

exit 0
