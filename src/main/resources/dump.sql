--
-- PostgreSQL database dump
--

-- Dumped from database version 13.6 (Ubuntu 13.6-0ubuntu0.21.10.1)
-- Dumped by pg_dump version 13.6 (Ubuntu 13.6-0ubuntu0.21.10.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: music; Type: TABLE; Schema: public; Owner: cestpassion
--

CREATE TABLE public.music (
    code integer NOT NULL,
    name text,
    arstist text,
    gender text
);


ALTER TABLE public.music OWNER TO cestpassion;

--
-- Name: music music_pkey; Type: CONSTRAINT; Schema: public; Owner: cestpassion
--

ALTER TABLE ONLY public.music
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (code);


--
-- PostgreSQL database dump complete
--
