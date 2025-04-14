-- Пользователи сайта
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(100) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       created_at TIMESTAMPTZ DEFAULT NOW(),
                        role VARCHAR(100) not null

);

-- Фильмы
CREATE TABLE movies (
                        id BIGSERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        video_url TEXT NOT NULL,
                        thumbnail_url TEXT,
                        uploaded_at TIMESTAMPTZ DEFAULT NOW()
);

-- Субтитры
CREATE TABLE subtitles (
                           id BIGSERIAL PRIMARY KEY,
                           movie_id BIGINT NOT NULL REFERENCES movies(id) ON DELETE CASCADE,
                           start_time DOUBLE PRECISION NOT NULL,  -- секунды начала фразы
                           end_time DOUBLE PRECISION NOT NULL,    -- секунды окончания
                           text TEXT NOT NULL
);

-- Переводы, сделанные пользователями
CREATE TABLE subtitle_translations (
                                       id BIGSERIAL PRIMARY KEY,
                                       user_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
                                       subtitle_id BIGINT NOT NULL REFERENCES subtitles(id) ON DELETE CASCADE,
                                       selected_text TEXT NOT NULL,           -- текст, который выделил пользователь
                                       translated_text TEXT NOT NULL,         -- перевод от LibreTranslate
                                       source_lang VARCHAR(10) NOT NULL,
                                       target_lang VARCHAR(10) NOT NULL,
                                       translated_at TIMESTAMPTZ DEFAULT NOW()
);
