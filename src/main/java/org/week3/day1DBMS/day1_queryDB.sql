Select * from public.clienti;

Select * from public.clienti where nome = 'Mario';

Select nome, cognome from public.clienti where annoNascita = 1992;

select * from public.fatture;

select * from public.fornitori;

select * from public.fatture where iva = 20;

select * from public.prodotti
where extract(year from dataattivazione) = 2024 
and (prodotti.inproduzione = true or prodotti.incommercio = true);

select f.idfattura,f.importo,f.iva,f.datafattura, fr.denominazione
from public.fatture f join public.fornitori fr on
f.idfornitore = fr.idfornitore;

select extract(year from datafattura) as anno,
count(*)as numero_fatture
from public.fatture where iva =20
group by extract(year from datafattura);

select extract(year from datafattura) as anno,
count(*)as numero_fatture,
sum(importo) as importo_totale
from public.fatture
group by extract(year from datafattura);

select extract(year from datafattura) as anno,
count(*) as numero_fatture
from public.fatture
where tipologia ='pubblica'
group by extract(year from datafattura)
having count(*)>=2;

select
c.regioneresidenza,
sum(f.importo) as importo_totale,
count(*) as n_fatture
from public.fatture f 
join public.clienti c on f.idcliente = c.numerocliente
group by c.regioneresidenza;

select 
c.annonascita,
count(*) as numero_di_clienti
from public.fatture f 
join public.clienti c on f.idcliente = c.numerocliente
where c.annonascita = 1991 and f.importo > 50
group by c.annonascita;



