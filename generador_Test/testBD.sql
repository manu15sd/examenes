create table autor (
	nombre varchar(70) not null,
	idAutor int primary key AUTO_INCREMENT
);

create table grupo(
	idGrupo int primary key AUTO_INCREMENT,
	nombreOposicion varchar(70)
);

create table ley(
	nombreLey varchar(70) primary key,
	numMaxArticulo int
);

create table tema(
	idTema int primary key AUTO_INCREMENT,
	nombreTema varchar(70),
	subtema varchar(70)
);

create table grupoLey(
	idGrupo int,
	nombreLey varchar(70),
	foreign key (idGrupo) references grupo(idGrupo),
	foreign key (nombreLey) references ley(nombreLey),
	CONSTRAINT PK_grupoLeyTema PRIMARY KEY (idGrupo,nombreLey)
);

create table grupoTema(
	idGrupo int,
	idTema int,
	foreign key (idGrupo) references grupo(idGrupo),
	foreign key (idTema) references tema(idTema),
	CONSTRAINT PK_grupoLeyTema PRIMARY KEY (idGrupo,idTema)
);



create table pregLegislacion(
	idLegislacion int primary key AUTO_INCREMENT,
	img varchar(100),
	fecha date not null,
	ley varchar(70), 
	art int,
	disposicion boolean,
	enunciado text not null,
	idAutor int,
	foreign key (ley) references ley(nombreLey),
	foreign key (idAutor) references autor(idAutor)
	
);

create table pregTemas(
	idPreguntaTemas int primary key AUTO_INCREMENT,
	img varchar(100),
	fecha date not null,
	enunciado text not null,
	idAutor int,
	idTema int,
	foreign key (idTema) references tema(idTema),
	foreign key (idAutor) references autor(idAutor)
	
);

create table respuestas(
	idRespuesta int primary key AUTO_INCREMENT,
	texto varchar(250) not null,
	correcta boolean not null,
	idLegislacion int,
	idPreguntaTemas int,
	foreign key (idPreguntaTemas) references pregTemas(idPreguntaTemas),
	foreign key (idLegislacion) references pregLegislacion(idLegislacion)
);

DELETE FROM pregTemas WHERE idPreguntaTemas between 13 and 18;
DELETE FROM respuestas WHERE idPreguntaTemas between 13 and 18;
DELETE FROM pregLegislacion WHERE idLegislacion between 14 and 16;
DELETE FROM respuestas WHERE idLegislacion between 14 and 16;
select * from pregLegislacion;
alter table autor drop column idAutor;
select *from respuestas where texto like '%alto%';
update respuestas set texto='el alto comionado para asuntos económicos', correcta=false where idRespuesta = 44;
update pregTemas set idAutor=4, idTema=25, enunciado='La primera conexión de computadoras fue conocida como:'  where idPreguntaTemas = 25;
update pregTemas set idAutor=4, idTema=25, enunciado='La	primera	conexión	de	computadoras	fue	conocida	como:'  where idPreguntaTemas = 3

update pregTemas set idAutor=?, idTema=?, enunciado=?,  where idPreguntaTemas = ?
insert into pregTemas(fecha, enunciado, idAutor, idTema) values ();
insert into pregLegislacion(fecha, ley, art, disposicion, enunciado, idAutor) values ();
insert into respuestas(texto, correcta, idLegislacion, idPreguntaTemas) values();

show tables;

insert into grupo(nombreOposicion) values ('A1/A2 Xunta');
insert into autor(nombre) values ('Rafael Martínez');
insert into ley values ('Ley de contratos',347);
insert into tema(nombreTema,subtema) values ('La hacienda de Galicia','El presupuesto');
insert into grupoLey values (8,'Constitución Española');
insert into grupoLey values (12,'Estatuto de Autonomía');
insert into grupoLey values (12,'Ley 40 de Procedimiento');
insert into grupoLey values (12,'Ley 39 de Procedimiento');
insert into grupoTema values (12,42);
insert into grupoTema values (12,43);
insert into grupoTema values (12,44);
insert into grupoTema values (12,45);
insert into grupoTema values (12,46);
insert into grupoTema values (12,37);
insert into grupoTema values (12,38);
insert into grupoTema values (10,16);
insert into grupoTema values (10,17);
insert into grupoTema values (10,110);
insert into grupoTema values (10,110);
insert into grupoTema values (10,20);
insert into grupoTema values (10,28);
insert into grupoTema values (10,210);
insert into grupoTema values (10,30);
insert into grupoTema values (10,31);

select idtema from tema where subtema like ?;

insert into pregTemas(fecha, enunciado, idAutor, idTema) values (?,?,?,?)

select * from tema;
select * from autor;
select * from pregTemas;
update pregTemas set idAutor=?, idTema=?, enunciado=?,  where idPreguntaTemas = ?;
select Any_Value(*) from pregLegislacion join ley on ley.nombreley =  pregLegislacion.ley join autor on autor.idAutor = pregLegislacion.idAutor join grupoLey on grupoLey.nombreLey = ley.nombreLey join grupo on grupo.idgrupo = grupoley.idgrupo where nombre LIKE '%raf%' group by pregLegislacion.idLegislacion;

delete from pregTemas;
delete from pregLegislacion;
delete from respuestas;
select * from tema;
select * from ley where kl =3;
select * from pregTemas join tema on  pregTemas.idTema = tema.idTema join autor on autor.idAutor = pregTemas.idAutor join grupoTema on grupoTema.idTema = tema.idTema join grupo on grupoTema.idGrupo = grupo.idGrupo  where nombre like '%ra%' group by idPreguntaTemas;
select * from pregLegislacion join ley on ley.nombreley =  pregLegislacion.ley join autor on autor.idAutor = pregLegislacion.idAutor join grupoLey on grupoLey.nombreLey = ley.nombreLey join grupo on grupo.idgrupo = grupoley.idgrupo where nombre LIKE '%ra%' group by idLegislacion;

update pregTemas 
set enunciado = 'Entre las responsabilidades básicas del consejo de europa, ¿Cuál de éstas no forma parte de ellas?' 
where idPreguntaTemas = 7;

select * from pregLegislacion join ley on ley.nombreley =  pregLegislacion.ley;
select * from pregLegislacion where ley = 'Constitución Española' AND art between '1' and '5' ;
select * from respuestas where idTema = 28;
select * from pregTemas join tema on  pregTemas.idTema = tema.idTema where subtema = 'Recomendaciones';
select * from pregTemas join tema on  pregTemas.idTema = tema.idTema where nombreTema = 'La Unión Europea';
select * from pregLegislacion;
select * from respuestas;
select * from grupoLey join Ley on grupoLey.nombreLey = Ley.nombreLey where idGrupo = 8  ;
select * from grupoTema join Tema on grupoTema.idTema = tema.idTema where idGrupo = 7   ;
select subtema from Tema where nombreTema = 'Administración General del Estado';
select * from grupo join grupoLey on grupo.idGrupo = grupoLey.idGrupo join pregLegislacion on grupoLey.nombreLey = pregLegislacion.ley where grupo.nombreOposicion = 'Sub Xunta';
select * from respuestas where idLegislacion = 6;
select * from grupo join grupoTema on grupo.idGrupo = grupoTema.idGrupo join pregTemas on grupoTema.idTema = pregTemas.idTema join tema on pregTemas.idTema = tema.idTema  where grupo.nombreOposicion = 'Sub Xunta';

desc respuestas;

drop table respuestas;
drop table pregTemas;
drop table pregLegislacion;
drop table grupoley;
drop table grupoTema;
drop table tema;
drop table ley;
drop table grupo;
drop table autor;
desc leyTema;

select * from generadorTest_schema; 

select * from pregLegislacion join ley on  pregLegislacion.idTema = tema.idTema 

