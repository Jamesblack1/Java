package com.core.api.mapper;

import com.core.api.models.Persona;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    @Select("SELECT NOMBRE FROM PERSONA WHERE ID = #{id}")
    String getNombre(@Param("id") Integer id);

    @Select("SELECT * FROM PERSONA WHERE ID = #{id}")
    Persona getPersona(@Param("id") Integer id);

    @Insert("INSERT INTO PERSONA(NOMBRE, APELLIDO) VALUES (#{nombre}, #{apellido})")
    Integer insertaPersona(Persona persona);

    @Delete("DELETE FROM PERSONA WHERE ID = #{id}")
    Integer deletePersona(@Param("id") Integer id);

    @Select("SELECT * FROM PERSONA")
    List<Persona> getListPersonas();

    @Update("UPDATE PERSONA SET NOMBRE = #{nombre}, APELLIDO = #{apellido} WHERE ID = #{id}")
    Integer actualizaPersona(Persona persona);
}
