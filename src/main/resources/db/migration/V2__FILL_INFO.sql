INSERT INTO public.client_status
    (id, "percent", "name")
VALUES (0, 0.00, 'NEW');
INSERT INTO public.client_status
    (id, "percent", "name")
VALUES (1, 0.00, 'OLD');

INSERT INTO public.service_group
    (id, "name")
VALUES (0, 'Маникюр');
INSERT INTO public.service_group
    (id, "name")
VALUES (1, 'Педикюр');

INSERT INTO public.service
    (id, group_id, "name")
VALUES (0, 0, 'Без покрытия');
INSERT INTO public.service
    (id, group_id, "name")
VALUES (1, 0, 'С покрытием');
INSERT INTO public.service
    (id, group_id, "name")
VALUES (2, 0, 'Френч');
INSERT INTO public.service
    (id, group_id, "name")
VALUES (3, 0, 'Дизайн');
INSERT INTO public.service
    (id, group_id, "name")
VALUES (4, 0, 'Парафинотерапия');
INSERT INTO public.service
    (id, group_id, "name")
VALUES (5, 0, 'Ремонт ногтей');
INSERT INTO public.service
    (id, group_id, "name")
VALUES (6, 1, 'Без покрытия');
INSERT INTO public.service
    (id, group_id, "name")
VALUES (7, 1, 'С покрытием');
INSERT INTO public.service
    (id, group_id, "name")
VALUES (8, 1, 'Экспресс с покрытием');

INSERT INTO public.contact
(id, insta_link, telegram_nick, viber_link, whats_app_link, work_insta_link)
VALUES (0, 'https://instagram.com/nastasi.paramonova?igshid=MmIzYWVlNDQ5Yg==', '@NastasyP', NULL, NULL,
        'https://instagram.com/nails.me.spb?igshid=OGQ5ZDc2ODk2ZA==');
INSERT INTO public.master
    (id, contact_id, "name")
VALUES (0, 0, 'Анастасия Парамонова');