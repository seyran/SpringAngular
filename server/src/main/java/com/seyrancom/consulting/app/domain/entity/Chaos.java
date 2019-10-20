package com.seyrancom.consulting.app.domain.entity;

import com.seyrancom.consulting.core.domain.entity.AbstractEntity;
/*

@Entity
@Table(name="chaos")
@SQLInsert( sql="INSERT INTO CHAOS(size, name, nickname, id) VALUES(?,upper(?),?,?)")
@SQLUpdate( sql="UPDATE CHAOS SET size = ?, name = upper(?), nickname = ? WHERE id = ?")
@SQLDelete( sql="DELETE CHAOS WHERE id = ?")
@SQLDeleteAll( sql="DELETE CHAOS")
@Loader(namedQuery = "chaos")
@NamedNativeQuery(name="chaos", query="select id, size, name, lower( nickname ) as nickname from CHAOS where xml:id= ?", resultClass = Chaos.class)

*/

public class Chaos extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    protected String name;

    /*@OneToMany
    @JoinColumn(name="chaos_fk")
    @SQLInsert( sql="UPDATE CASIMIR_PARTICULE SET chaos_fk = ? where id = ?")
    @SQLDelete( sql="UPDATE CASIMIR_PARTICULE SET chaos_fk = null where id = ?")
    private Set<CasimirParticle> particles = new HashSet<CasimirParticle>();*/
}