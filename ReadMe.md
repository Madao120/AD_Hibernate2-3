

### Relaciones
| | ManyToOne | OneToMany | OneToOne |
|:--- | :--- | :--- | :--- |
| Anotaciones | @OneToMany <br> @JoinColumn(name = "nombre_columna") <br> private ClaseOne nombre_clase_one | @OneToMany( <br> mappedBy = "nombre_columna", <br> cascade = CascadeType.ALL <br> fetch = FetchType.EAGER) <br>private ist<ClaseMany> nombre_clase_many | Dato 1C |
| Explicación | Proviene de la clase de varios Objetos | Proviene de la clase Unica | Dato 2C |