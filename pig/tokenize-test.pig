REGISTER pig/lib/caissepop-1.2*.jar;
REGISTER pig/lib/lucene-*.jar;

define TokenizeText com.fujitsu.ca.fic.caissepop.evaluation.TokenizeText();

documents    = LOAD '$INPUT' USING PigStorage('\n', '-tagsource') 
                AS (doc_id:chararray, text:chararray);

-- specify a regex operation to split the "document" text lines into a token stream
out = FOREACH documents GENERATE doc_id,
             FLATTEN(TokenizeText(text)) 
             AS (token:chararray);
out = FILTER out BY token MATCHES '\\w.*';

dump out;           
--STORE out INTO '$OUTPUT' using PigStorage('\n');
