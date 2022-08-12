INSERT INTO public.issue_types (id, "name") VALUES(1, 'General');

INSERT INTO public.issues (id, author_id, type_id, status, "text") VALUES(1, NULL, 1, 'NEW', 'Issue1');
INSERT INTO public.issues (id, author_id, type_id, status, "text") VALUES(2, NULL, 1, 'NEW', 'Issue2');
INSERT INTO public.issues (id, author_id, type_id, status, "text") VALUES(3, NULL, 1, 'NEW', 'Issue3');

INSERT INTO public."comments" (id, date_time, "text", author_id, issue_id)
VALUES(10, '2022-05-25 19:51:26.654', 'comment1', NULL, 1);

INSERT INTO public."comments" (id, date_time, "text", author_id, issue_id)
VALUES(11, '2022-05-25 19:51:26.654', 'comment2', NULL, 1);

INSERT INTO public."comments" (id, date_time, "text", author_id, issue_id)
VALUES(12, '2022-05-25 19:51:26.654', 'comment3', NULL, 2);


