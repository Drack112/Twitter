CREATE SEQUENCE users_seq START 100 INCREMENT 1;

CREATE TABLE users
(
    id                       INT8         NOT NULL,
    about                    VARCHAR(255),
    activation_code          VARCHAR(255),
    active                   BOOLEAN      NOT NULL DEFAULT FALSE,
    avatar                   VARCHAR(255),
    background_color         VARCHAR(255) NOT NULL DEFAULT 'DEFAULT',
    color_scheme             VARCHAR(255) NOT NULL DEFAULT 'BLUE',
    birthday                 VARCHAR(255),
    country_code             VARCHAR(255),
    country                  VARCHAR(255),
    phone_code               VARCHAR(255),
    phone_number             INT8,
    email                    VARCHAR(255) NOT NULL UNIQUE,
    full_name                VARCHAR(255) NOT NULL,
    gender                   VARCHAR(255),
    language                 VARCHAR(255),
    like_count               INT8         NOT NULL DEFAULT 0,
    location                 VARCHAR(255),
    media_tweet_count        INT8         NOT NULL DEFAULT 0,
    muted_direct_messages    BOOLEAN      NOT NULL DEFAULT FALSE,
    notifications_count      INT8         NOT NULL DEFAULT 0,
    mentions_count           INT8         NOT NULL DEFAULT 0,
    followers_count          INT8         NOT NULL DEFAULT 0,
    following_count          INT8         NOT NULL DEFAULT 0,
    followers_requests_count INT8         NOT NULL DEFAULT 0,
    password                 VARCHAR(255),
    password_reset_code      VARCHAR(255),
    pinned_tweet_id          INT8,
    private_profile          BOOLEAN      NOT NULL DEFAULT FALSE,
    profile_customized       BOOLEAN      NOT NULL DEFAULT FALSE,
    profile_started          BOOLEAN      NOT NULL DEFAULT FALSE,
    registration_date        TIMESTAMP    NOT NULL DEFAULT current_timestamp,
    updated_at               TIMESTAMP,
    role                     VARCHAR(255) NOT NULL,
    tweet_count              INT8         NOT NULL DEFAULT 0,
    unread_messages_count    INT8         NOT NULL DEFAULT 0,
    username                 VARCHAR(255) NOT NULL,
    wallpaper                VARCHAR(255),
    website                  VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE subscribers
(
    user_id       INT8 NOT NULL REFERENCES users (id),
    subscriber_id INT8 NOT NULL REFERENCES users (id)
);
CREATE INDEX subscribers_user_id_idx ON subscribers (user_id);
CREATE INDEX subscribers_subscriber_id_idx ON subscribers (subscriber_id);

CREATE TABLE user_blocked
(
    user_id         INT8 NOT NULL REFERENCES users (id),
    blocked_user_id INT8 NOT NULL REFERENCES users (id)
);
CREATE INDEX user_blocked_user_id_idx ON user_blocked (user_id);
CREATE INDEX user_blocked_blocked_user_id_idx ON user_blocked (blocked_user_id);

CREATE TABLE user_follower_requests
(
    user_id     INT8 NOT NULL REFERENCES users (id),
    follower_id INT8 NOT NULL REFERENCES users (id)
);
CREATE INDEX user_follower_requests_user_id_idx ON user_follower_requests (user_id);
CREATE INDEX user_follower_requests_follower_id_idx ON user_follower_requests (follower_id);

CREATE TABLE user_muted
(
    user_id       INT8 NOT NULL REFERENCES users (id),
    muted_user_id INT8 NOT NULL REFERENCES users (id)
);
CREATE INDEX user_muted_user_id_idx ON user_muted (user_id);
CREATE INDEX user_muted_muted_user_id_idx ON user_muted (muted_user_id);

CREATE TABLE user_subscriptions
(
    user_id       INT8 NOT NULL REFERENCES users (id),
    subscriber_id INT8 NOT NULL REFERENCES users (id)
);
CREATE INDEX user_subscriptions_user_id_idx ON user_subscriptions (user_id);
CREATE INDEX user_subscriptions_subscriber_id_idx ON user_subscriptions (subscriber_id);

-- User 1
INSERT INTO public.users
(id, about, activation_code, active, avatar, background_color, color_scheme, birthday, country_code, country, phone_code, phone_number, email, full_name, gender, "language", like_count, "location", media_tweet_count, muted_direct_messages, notifications_count, mentions_count, followers_count, following_count, followers_requests_count, "password", password_reset_code, pinned_tweet_id, private_profile, profile_customized, profile_started, registration_date, updated_at, "role", tweet_count, unread_messages_count, username, wallpaper, website)
VALUES(1, 'Tech enthusiast and blogger', 'ACT1234', true, 'avatar1.jpg', 'DEFAULT', 'CRIMSON', '1990-01-01', 'US', 'United States', '+1', 1234567890, 'user@example.com', 'John Doe', 'Male', 'EN', 10, 'New York', 5, false, 2, 3, 150, 100, 0, 'password_hash_here', NULL, NULL, false, true, true, '2024-10-29 12:59:18.488', NULL, 'USER', 50, 1, 'johndoe', 'wallpaper.jpg', 'https://johndoe.com');

-- User 2
INSERT INTO public.users (
    id, about, activation_code, active, avatar, background_color, color_scheme, birthday, country_code, 
    country, phone_code, phone_number, email, full_name, gender, "language", like_count, "location", 
    media_tweet_count, muted_direct_messages, notifications_count, mentions_count, followers_count, 
    following_count, followers_requests_count, "password", password_reset_code, pinned_tweet_id, 
    private_profile, profile_customized, profile_started, registration_date, updated_at, "role", 
    tweet_count, unread_messages_count, username, wallpaper, website
) VALUES (
    2, 'Photographer and world traveler', 'ACT5678', true, 'avatar2.jpg','DEFAULT', 'CRIMSON', '1985-05-15', 
    'CA', 'Canada', '+1', 9876543210, 'user2@example.com', 'Jane Smith', 'Female', 'EN', 25, 
    'Toronto', 10, false, 5, 2, 200, 150, 1, 'password_hash_2', NULL, NULL, false, true, true, 
    DEFAULT, NULL, 'USER', 100, 0, 'janesmith', 'wallpaper2.jpg', 'https://janesmithphotography.com'
);

-- User 3
INSERT INTO public.users (
    id, about, activation_code, active, avatar, background_color, color_scheme, birthday, country_code, 
    country, phone_code, phone_number, email, full_name, gender, "language", like_count, "location", 
    media_tweet_count, muted_direct_messages, notifications_count, mentions_count, followers_count, 
    following_count, followers_requests_count, "password", password_reset_code, pinned_tweet_id, 
    private_profile, profile_customized, profile_started, registration_date, updated_at, "role", 
    tweet_count, unread_messages_count, username, wallpaper, website
) VALUES (
    3, 'Gamer and live streamer', 'ACT9876', true, 'avatar3.png', 'DEFAULT', 'CRIMSON', '1997-08-10', 
    'UK', 'United Kingdom', '+44', 1231231234, 'user3@example.com', 'Mike Johnson', 'Male', 'EN', 
    50, 'London', 30, false, 10, 8, 300, 175, 2, 'password_hash_3', NULL, NULL, true, true, 
    true, DEFAULT, NULL, 'USER', 200, 2, 'mikejohnson', 'wallpaper3.png', NULL
);

-- User 4
INSERT INTO public.users (
    id, about, activation_code, active, avatar, background_color, color_scheme, birthday, country_code, 
    country, phone_code, phone_number, email, full_name, gender, "language", like_count, "location", 
    media_tweet_count, muted_direct_messages, notifications_count, mentions_count, followers_count, 
    following_count, followers_requests_count, "password", password_reset_code, pinned_tweet_id, 
    private_profile, profile_customized, profile_started, registration_date, updated_at, "role", 
    tweet_count, unread_messages_count, username, wallpaper, website
) VALUES (
    4, 'Fitness coach and nutritionist', 'ACT5432', true, 'avatar4.jpg', 'DEFAULT', 'CRIMSON', '1992-02-20', 
    'AU', 'Australia', '+61', 3213213210, 'user4@example.com', 'Sarah Williams', 'Female', 'EN', 
    75, 'Sydney', 40, true, 15, 5, 400, 300, 3, 'password_hash_4', NULL, NULL, false, true, 
    true, DEFAULT, NULL, 'USER', 150, 1, 'sarahwilliams', 'wallpaper4.jpg', 'https://sarahfit.com'
);

-- User 5
INSERT INTO public.users (
    id, about, activation_code, active, avatar, background_color, color_scheme, birthday, country_code, 
    country, phone_code, phone_number, email, full_name, gender, "language", like_count, "location", 
    media_tweet_count, muted_direct_messages, notifications_count, mentions_count, followers_count, 
    following_count, followers_requests_count, "password", password_reset_code, pinned_tweet_id, 
    private_profile, profile_customized, profile_started, registration_date, updated_at, "role", 
    tweet_count, unread_messages_count, username, wallpaper, website
) VALUES (
    5, 'Digital artist and designer', 'ACT1122', true, 'avatar5.png', 'DEFAULT', 'CRIMSON', '1988-12-30', 
    'FR', 'France', '+33', 1111111111, 'user5@example.com', 'Emma Brown', 'Female', 'FR', 90, 
    'Paris', 20, false, 3, 7, 500, 400, 4, 'password_hash_5', NULL, NULL, false, true, 
    false, DEFAULT, NULL, 'USER', 250, 3, 'emmabrown', 'wallpaper5.jpg', 'https://emmabrowndesign.com'
);

-- User 6
INSERT INTO public.users (
    id, about, activation_code, active, avatar, background_color, color_scheme, birthday, country_code, 
    country, phone_code, phone_number, email, full_name, gender, "language", like_count, "location", 
    media_tweet_count, muted_direct_messages, notifications_count, mentions_count, followers_count, 
    following_count, followers_requests_count, "password", password_reset_code, pinned_tweet_id, 
    private_profile, profile_customized, profile_started, registration_date, updated_at, "role", 
    tweet_count, unread_messages_count, username, wallpaper, website
) VALUES (
    6, 'Software developer and tech writer', 'ACT3344', true, 'avatar6.jpg', 'DEFAULT', 'CRIMSON', '1995-06-25', 
    'IN', 'India', '+91', 2222222222, 'user6@example.com', 'Ravi Kumar', 'Male', 'EN', 120, 
    'Mumbai', 15, true, 6, 9, 600, 500, 0, 'password_hash_6', NULL, NULL, true, true, 
    true, DEFAULT, NULL, 'USER', 300, 0, 'ravikumar', 'wallpaper6.jpg', 'https://ravikumar.dev'
);

-- User 1 is subscribed to by User 2 and User 3
INSERT INTO public.subscribers (user_id, subscriber_id) VALUES (1, 2);
INSERT INTO public.subscribers (user_id, subscriber_id) VALUES (1, 3);

-- User 2 is subscribed to by User 1 and User 4
INSERT INTO public.subscribers (user_id, subscriber_id) VALUES (2, 1);
INSERT INTO public.subscribers (user_id, subscriber_id) VALUES (2, 4);

-- User 3 is subscribed to by User 1 and User 5
INSERT INTO public.subscribers (user_id, subscriber_id) VALUES (3, 1);
INSERT INTO public.subscribers (user_id, subscriber_id) VALUES (3, 5);

-- User 4 is subscribed to by User 2 and User 6
INSERT INTO public.subscribers (user_id, subscriber_id) VALUES (4, 2);
INSERT INTO public.subscribers (user_id, subscriber_id) VALUES (4, 6);

-- User 5 is subscribed to by User 3 and User 4
INSERT INTO public.subscribers (user_id, subscriber_id) VALUES (5, 3);
INSERT INTO public.subscribers (user_id, subscriber_id) VALUES (5, 4);

-- User 6 is subscribed to by User 1 and User 5
INSERT INTO public.subscribers (user_id, subscriber_id) VALUES (6, 1);
INSERT INTO public.subscribers (user_id, subscriber_id) VALUES (6, 5);
