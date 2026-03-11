package com.fatec.lddm_merge_skills.repository

/**
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║  Interface: CourseRepository — Contrato de Acesso a Dados       ║
 * ╚══════════════════════════════════════════════════════════════════╝
 *
 * 🔑 CONCEITO: Repository Pattern
 * ────────────────────────────────
 * Uma interface define O QUE deve ser feito, não COMO.
 * Isso permite ter diferentes implementações:
 *   - SupabaseCourseRepository → usa PostgREST (API REST)
 *   - ExposedCourseRepository  → usa JDBC (conexão direta)
 *   - MockCourseRepository     → dados fictícios para testes
 *
 * O código que CHAMA o repositório não sabe (e não precisa saber)
 * qual implementação está sendo usada. Isso é o princípio da
 * Inversão de Dependência (D do SOLID).
 *
 * 📍 LOCALIZAÇÃO:
 * A interface fica no `shared` porque o Android também a usa.
 * A implementação fica no `server` (ou na camada de rede do Android).
 */

import com.fatec.lddm_merge_skills.model.Course

interface CourseRepository {
    /**
     * Retorna todos os cursos disponíveis.
     * `suspend` = função assíncrona (não bloqueia a thread principal)
     */
    suspend fun getAll(): List<Course>

    /**
     * Retorna um curso pelo ID, ou null se não existir.
     */
    suspend fun getById(id: Int): Course?

    /**
     * Cria um novo curso e retorna o curso criado (com ID gerado).
     */
    suspend fun create(course: Course): Course

    /**
     * Atualiza um curso existente e retorna o curso atualizado.
     */
    suspend fun update(id: Int, course: Course): Course

    /**
     * Deleta um curso pelo ID.
     */
    suspend fun delete(id: Int)
}