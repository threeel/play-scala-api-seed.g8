--
-- PostgreSQL database dump
--

-- Dumped from database version 10.4
-- Dumped by pg_dump version 10.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: user_auth_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_auth_info (
    user_id integer NOT NULL,
    login_info_id integer NOT NULL
);


ALTER TABLE public.user_auth_info OWNER TO postgres;

--
-- Name: user_auth_tokens; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_auth_tokens (
    id integer NOT NULL,
    user_id integer NOT NULL,
    token character varying(100) NOT NULL,
    expiry timestamp without time zone NOT NULL
);


ALTER TABLE public.user_auth_tokens OWNER TO postgres;

--
-- Name: user_auth_tokens_id_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_auth_tokens_id_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_auth_tokens_id_seq1 OWNER TO postgres;

--
-- Name: user_auth_tokens_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_auth_tokens_id_seq1 OWNED BY public.user_auth_tokens.id;


--
-- Name: user_login_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_login_info (
    id integer NOT NULL,
    provider_id character varying(45),
    provider_key character varying(45),
    created_at timestamp without time zone,
    updated_at timestamp without time zone
);


ALTER TABLE public.user_login_info OWNER TO postgres;

--
-- Name: user_login_info_id_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_login_info_id_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_login_info_id_seq1 OWNER TO postgres;

--
-- Name: user_login_info_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_login_info_id_seq1 OWNED BY public.user_login_info.id;


--
-- Name: user_password_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_password_info (
    login_info_id integer NOT NULL,
    hasher character varying(100),
    password character varying(100),
    salt character varying(100),
    created_at timestamp without time zone,
    updated_at timestamp without time zone
);


ALTER TABLE public.user_password_info OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(64) NOT NULL,
    email character varying(64) NOT NULL,
    phone character varying(20),
    email_confirmed timestamp without time zone,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now()
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: TABLE users; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.users IS 'Users Table';


--
-- Name: users_id_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq1 OWNER TO postgres;

--
-- Name: users_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq1 OWNED BY public.users.id;


--
-- Name: user_auth_tokens id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_auth_tokens ALTER COLUMN id SET DEFAULT nextval('public.user_auth_tokens_id_seq1'::regclass);


--
-- Name: user_login_info id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_login_info ALTER COLUMN id SET DEFAULT nextval('public.user_login_info_id_seq1'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq1'::regclass);


--
-- Data for Name: user_auth_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_auth_info (user_id, login_info_id) VALUES (1, 1);


--
-- Data for Name: user_auth_tokens; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_auth_tokens (id, user_id, token, expiry) VALUES
(1, 1, '5FC98393-DA07-4DC7-8548-FDFF6E797820', '2018-08-20 13:35:47.229');


--
-- Data for Name: user_login_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_login_info (id, provider_id, provider_key, created_at, updated_at) VALUES
(1, 'credentials', 'user@example.com', '2018-08-13 13:27:30.140023', '2018-08-13 13:27:30.140023');


--
-- Data for Name: user_password_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_password_info (login_info_id, hasher, password, salt, created_at, updated_at) VALUES
(1, 'bcrypt', '$2a$10$FeI4FREaR8yHmmTWLAdEW.i4BAt6orU9miXPJwf78Ib3VGTH3Cut2', NULL, '2018-08-13 13:27:30.140023', '2018-08-13 13:27:30.140023');

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, name, email, phone, email_confirmed, created_at, updated_at) VALUES
(1, 'John Doe', 'user@example.com', NULL, NULL, '2018-08-13 13:36:03.251883', '2018-08-13 13:36:03.251883');

--
-- Name: user_auth_tokens_id_seq1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_auth_tokens_id_seq1', 9, true);


--
-- Name: user_login_info_id_seq1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_login_info_id_seq1', 10, true);


--
-- Name: users_id_seq1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq1', 19, true);


--
-- Name: user_auth_tokens user_auth_tokens_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_auth_tokens
    ADD CONSTRAINT user_auth_tokens_pkey PRIMARY KEY (id);


--
-- Name: users users_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_id_pk PRIMARY KEY (id);


--
-- Name: user_auth_info_user_id_login_info_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX user_auth_info_user_id_login_info_id_uindex ON public.user_auth_info USING btree (user_id, login_info_id);


--
-- Name: user_auth_tokens_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX user_auth_tokens_id_uindex ON public.user_auth_tokens USING btree (id);


--
-- Name: users_email_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX users_email_uindex ON public.users USING btree (email);


--
-- Name: users_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--


CREATE UNIQUE INDEX users_id_uindex ON public.users USING btree (id);