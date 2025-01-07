update SYS_DICT
set DICT_NAME = '国卫办科教发〔2014〕52号'
WHERE DICT_FLOW = '7bedafade2ad45d9b1d3a575ec804e3e';

update SYS_DICT
set DICT_NAME = '国卫办科教函〔2017〕998号',
    DICT_TYPE_NAME = '江苏.住院医师基地获批文号',
    DICT_DESC = '国卫办科教函〔2017〕998号'
WHERE DICT_FLOW = '7bedafade2ad45d9b1d3a575ec804e3F';

update SYS_DICT
set DICT_NAME = '国卫办科教函〔2020〕970号',
    DICT_TYPE_NAME = '江苏.住院医师基地获批文号',
    DICT_DESC = '国卫办科教函〔2020〕970号'
WHERE DICT_FLOW = '7bedafade2ad45d9b1d3a575ec804e3D';
COMMIT;