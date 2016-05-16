package br.com.Meensina.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProfessorEspecialidadePK implements Serializable{
	
	private static final long serialVersionUID = -1284329599132475880L;
	
	private Long professorId;
	
	private Long especialidadeId;

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public Long getEspecialidadeId() {
		return especialidadeId;
	}

	public void setEspecialidadeId(Long especialidadeId) {
		this.especialidadeId = especialidadeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((especialidadeId == null) ? 0 : especialidadeId.hashCode());
		result = prime * result + ((professorId == null) ? 0 : professorId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfessorEspecialidadePK other = (ProfessorEspecialidadePK) obj;
		if (especialidadeId == null) {
			if (other.especialidadeId != null)
				return false;
		} else if (!especialidadeId.equals(other.especialidadeId))
			return false;
		if (professorId == null) {
			if (other.professorId != null)
				return false;
		} else if (!professorId.equals(other.professorId))
			return false;
		return true;
	}
}